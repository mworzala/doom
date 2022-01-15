#version 150

#moj_import <light.glsl>
#moj_import <doom_util.glsl>

in vec3 Position;
in vec4 Color;
in vec2 UV0;
in ivec2 UV2;
in vec3 Normal;

uniform sampler2D Sampler2;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform vec3 ChunkOffset;

out float vertexDistance;
out vec4 vertexColor;
out vec2 texCoord0;
out vec4 normal;

void main() {
    mat4 viewMat = erase_rotation(ModelViewMat);

    gl_Position = ProjMat * viewMat * vec4(Position + ChunkOffset, 1.0);

    vertexDistance = length((viewMat * vec4(Position + ChunkOffset, 1.0)).xyz);
    vertexColor = Color * minecraft_sample_lightmap(Sampler2, UV2);
    texCoord0 = UV0;
    normal = ProjMat * viewMat * vec4(Normal, 0.0);
}
