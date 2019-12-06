package com.example.sampleecommerce;

import java.util.ArrayList;

public interface LoadCartCallback {
    void preExecute();
    void postexecute(ArrayList<ProductModel> productModel);
}
