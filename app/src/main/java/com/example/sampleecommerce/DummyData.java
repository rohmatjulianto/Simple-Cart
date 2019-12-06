package com.example.sampleecommerce;

import java.util.ArrayList;

public class DummyData {

    private static String[][] dataProduct = new String[][]{
            {"1","https://upload.wikimedia.org/wikipedia/commons/7/75/Selburose-sweater.jpg", "Sweater", "170000"},
            {"2","https://upload.wikimedia.org/wikipedia/commons/a/a5/Black_Converse_sneakers.JPG", "Sneakers", "450000"}
    };

    public static ArrayList<ProductModel> getListProduct(){
        ProductModel productModel;
        ArrayList<ProductModel> list = new ArrayList<>();
        for (String[] data : dataProduct){
            productModel = new ProductModel();
            productModel.setId(data[0]);
            productModel.setImage(data[1]);
            productModel.setName(data[2]);
            productModel.setPrice(data[3]);
            list.add(productModel);
        }
        return list;
    }
}
