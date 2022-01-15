#version 150

#moj_import <light.glsl>
#moj_import <fog.glsl>
#moj_import <vsh_util.glsl>
#moj_import <doom_util.glsl>

in vec3 Position;
in vec4 Color;
in vec2 UV0;
in ivec2 UV1;
in ivec2 UV2;
in vec3 Normal;

uniform sampler2D Sampler1;
uniform sampler2D Sampler2;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform mat3 IViewRotMat;

uniform vec3 Light0_Direction;
uniform vec3 Light1_Direction;

out float vertexDistance;
out vec4 vertexColor;
out vec4 lightMapColor;
out vec4 overlayColor;
out vec2 texCoord0;
out vec4 normal;

void main() {
    mat4 viewMat = mat4(getWorldMat(Light0_Direction, Light1_Direction));
    mat4 modelMat = ModelViewMat * inverse(viewMat);
    mat4 viewMat2 = erase_rotation(viewMat);
    mat4 newModelViewMat = viewMat2 * modelMat;

    gl_Position = ProjMat * newModelViewMat * vec4(Position, 1.0);

    vertexDistance = cylindrical_distance(newModelViewMat, IViewRotMat * Position);
    vertexColor = minecraft_mix_light(Light0_Direction, Light1_Direction, Normal, Color);
    lightMapColor = texelFetch(Sampler2, UV2 / 16, 0);
    overlayColor = texelFetch(Sampler1, UV1, 0);
    texCoord0 = UV0;
    normal = ProjMat * newModelViewMat * vec4(Normal, 0.0);
}
