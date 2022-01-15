
#define DOOM_LIGHT0_DIRECTION vec3(0.2, 1.0, -0.7)
#define DOOM_LIGHT1_DIRECTION vec3(-0.2, 1.0, 0.7)

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

// Duplicate of getWorldMat, but without a nether check.
mat3 getViewMat(vec3 light0, vec3 light1) {
    mat3 V = mat3(normalize(DOOM_LIGHT0_DIRECTION), normalize(DOOM_LIGHT1_DIRECTION), normalize(cross(DOOM_LIGHT0_DIRECTION, DOOM_LIGHT1_DIRECTION)));
    mat3 W = mat3(normalize(light0), normalize(light1), normalize(cross(light0, light1)));
    return W * inverse(V);
}

mat4 erase_rotation_non_uniform(mat4 ModelViewMat, vec3 Light0_Direction, vec3 Light1_Direction) {
    mat4 viewMat = mat4(getViewMat(Light0_Direction, Light1_Direction));
    mat4 modelMat = ModelViewMat * inverse(viewMat);
    mat4 viewMat2 = erase_rotation(viewMat);
    return viewMat2 * modelMat;
}
