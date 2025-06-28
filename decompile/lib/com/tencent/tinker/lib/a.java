/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib;


// class: com/tencent/tinker/lib/a
public interface a implements IInterface {

    void a();

    // class: com/tencent/tinker/lib/a$a
    public abstract class a$a implements a {

        public a$a() {
            super();
            this.attachInterface(this, "com.tencent.tinker.lib.IForeService");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i0, Parcel parcel, Parcel parcelVar1, int i1) {
String str0 = "com.tencent.tinker.lib.IForeService";
            switch(i0) {
                case 1598968902: {
                    parcelVar1.writeString(str0);
                    return true;
                }
                case 1: {
                    parcel.enforceInterface(str0);
                    this.a();
                    parcelVar1.writeNoException();
                    return true;
                }
                default: {
                    return super.onTransact(i0, parcel, parcelVar1, i1);
                }
            }
        }

    }
    // class: com/tencent/tinker/lib/a$a
    public abstract class a$a implements a {

        public a$a() {
            super();
            this.attachInterface(this, "com.tencent.tinker.lib.IForeService");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i0, Parcel parcel, Parcel parcelVar1, int i1) {
String str0 = "com.tencent.tinker.lib.IForeService";
            switch(i0) {
                case 1598968902: {
                    parcelVar1.writeString(str0);
                    return true;
                }
                case 1: {
                    parcel.enforceInterface(str0);
                    this.a();
                    parcelVar1.writeNoException();
                    return true;
                }
                default: {
                    return super.onTransact(i0, parcel, parcelVar1, i1);
                }
            }
        }

    }
}
