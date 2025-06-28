package com.tencent.tinker.loader.shareutil;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Helper class for acquiring and managing a file lock.
 * It attempts to lock a file with a limited number of retries and ensures
 * the lock is released and the associated file stream is closed.
 *
 * Note: This class was translated from a decompiled and obfuscated class `f.java`.
 */
public class ShareFileLockHelper implements Closeable {
    private final FileOutputStream fileOutputStream;
    private final FileLock fileLock;

    private static final int MAX_LOCK_ATTEMPTS = 3;
    private static final long LOCK_RETRY_SLEEP_INTERVAL = 10; // milliseconds

    private ShareFileLockHelper(File file) throws IOException {
        this.fileOutputStream = new FileOutputStream(file);
        FileLock lock = null;
        Throwable lastException = null;

        for (int attempts = 0; attempts < MAX_LOCK_ATTEMPTS; attempts++) {
            try {
                // Attempt to acquire an exclusive lock on the entire file.
                lock = this.fileOutputStream.getChannel().lock();
                if (lock != null) {
                    break; // Lock acquired successfully
                }
            } catch (Exception e) {
                lastException = e; // Record the exception
                // Using ShareTinkerLog.d, assuming it will be available.
                // The original log message "getInfoLock Thread failed time:10" seems to have a typo (10 vs attempts)
                ShareTinkerLog.d("Tinker.FileLockHelper", "Failed to acquire file lock on " + file.getAbsolutePath() +
                                                         ", attempt " + (attempts + 1) + "/" + MAX_LOCK_ATTEMPTS, e);
            }

            // If lock not acquired, wait before retrying (unless it's the last attempt)
            if (attempts < MAX_LOCK_ATTEMPTS - 1) {
                try {
                    Thread.sleep(LOCK_RETRY_SLEEP_INTERVAL);
                } catch (InterruptedException e) {
                    ShareTinkerLog.d("Tinker.FileLockHelper", "File lock retry sleep interrupted.", e);
                    // Preserve interrupt status
                    Thread.currentThread().interrupt();
                }
            }
        }

        if (lock == null) {
            String message = "Tinker Exception: Failed to acquire file lock on " + file.getAbsolutePath() +
                             " after " + MAX_LOCK_ATTEMPTS + " attempts.";
            if (lastException != null) {
                throw new IOException(message, lastException);
            } else {
                throw new IOException(message);
            }
        }
        this.fileLock = lock;
    }

    /**
     * Attempts to acquire a lock on the specified file.
     *
     * @param file The file to lock.
     * @return A ShareFileLockHelper instance managing the lock.
     * @throws IOException If the lock cannot be acquired after multiple attempts.
     */
    public static ShareFileLockHelper getFileLock(File file) throws IOException {
        return new ShareFileLockHelper(file);
    }

    /**
     * Releases the file lock and closes the underlying file stream.
     * This method should be called, typically in a finally block or by using try-with-resources.
     *
     * @throws IOException If an I/O error occurs during release or close.
     */
    @Override
    public void close() throws IOException {
        try {
            if (this.fileLock != null) {
                this.fileLock.release();
            }
        } finally {
            // Ensure the FileOutputStream is always closed, even if release() throws an exception.
            if (this.fileOutputStream != null) {
                this.fileOutputStream.close();
            }
        }
    }
}
