#version 150

#moj_import <fog.glsl>
#moj_import <doom_util.glsl>

in vec3 Position;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;

out float vertexDistance;

void main() {
    mat4 newModelViewMat = erase_rotation(ModelViewMat);

    gl_Position = ProjMat * newModelViewMat * vec4(Position, 1.0);

    vertexDistance = cylindrical_distance(newModelViewMat, Position);
}
