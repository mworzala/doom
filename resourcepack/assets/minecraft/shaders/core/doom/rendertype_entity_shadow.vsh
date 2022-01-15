#version 150

#moj_import <fog.glsl>
#moj_import <doom_util.glsl>

in vec3 Position;
in vec4 Color;
in vec2 UV0;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform mat3 IViewRotMat;

out float vertexDistance;
out vec4 vertexColor;
out vec2 texCoord0;

void main() {
    mat4 newModelViewMat = erase_rotation(ModelViewMat);

    gl_Position = ProjMat * newModelViewMat * vec4(Position, 1.0);

    vertexDistance = cylindrical_distance(newModelViewMat, IViewRotMat * Position);
    vertexColor = Color;
    texCoord0 = UV0;
}
