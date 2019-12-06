package com.example.sampleecommerce;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductModel implements Parcelable {

    String id;

    String price;
    String pcs;
    String name;
    String image;

    public ProductModel(String id, String price, String pcs, String name, String image) {
        this.id = id;
        this.price = price;
        this.pcs = pcs;
        this.name = name;
        this.image = image;
    }

    public ProductModel() {
    }

    public String getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public String getPcs() {
        return pcs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPcs(String pcs) {
        this.pcs = pcs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.price);
        dest.writeString(this.pcs);
        dest.writeString(this.name);
        dest.writeString(this.image);
    }

    protected ProductModel(Parcel in) {
        this.id = in.readString();
        this.price = in.readString();
        this.pcs = in.readString();
        this.name = in.readString();
        this.image = in.readString();
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel source) {
            return new ProductModel(source);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };
}
