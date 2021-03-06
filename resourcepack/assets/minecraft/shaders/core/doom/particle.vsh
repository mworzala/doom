#version 150

#moj_import <fog.glsl>
#moj_import <vsh_util.glsl>
#moj_import <doom_util.glsl>

in vec3 Position;
in vec2 UV0;
in vec4 Color;
in ivec2 UV2;

uniform sampler2D Sampler2;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;

uniform vec3 Light0_Direction;
uniform vec3 Light1_Direction;

out float vertexDistance;
out vec2 texCoord0;
out vec4 vertexColor;

void main() {
    mat4 viewMat = mat4(getWorldMat(Light0_Direction, Light1_Direction));
    mat4 modelMat = ModelViewMat * inverse(viewMat);
    mat4 viewMat2 = erase_rotation(viewMat);
    mat4 newModelViewMat = viewMat2 * modelMat;

    gl_Position = ProjMat * newModelViewMat * vec4(Position, 1.0);

    vertexDistance = cylindrical_distance(newModelViewMat, Position);
    texCoord0 = UV0;
    vertexColor = Color * texelFetch(Sampler2, UV2 / 16, 0);
}
