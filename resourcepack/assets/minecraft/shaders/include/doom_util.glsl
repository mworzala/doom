
/*
 * Erases the Y rotation from the view matrix.
 */
mat4 erase_rotation(mat4 ModelViewMat) {
    return mat4(
        vec4(ModelViewMat[0][0], 0, -ModelViewMat[2][0], 0),
        vec4(0, 1, 0, 0),
        vec4(ModelViewMat[2][0], 0, ModelViewMat[0][0], 0),
        vec4(0, 0, 0, 1)
    );
}
