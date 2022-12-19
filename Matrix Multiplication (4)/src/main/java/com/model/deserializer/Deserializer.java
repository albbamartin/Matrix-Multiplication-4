package com.model.deserializer;

import com.model.matrixes.Matrix;

import java.io.IOException;

public interface Deserializer {
    Matrix deserialize(String filename) throws IOException;
}
