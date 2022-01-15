#version 150

uniform sampler2D DiffuseSampler;

uniform vec4 ColorModulate;

uniform vec2 OutSize;

in vec2 texCoord;

out vec4 fragColor;

void main() {

    // Pixelation
    float onePixelSizeX = 1.0 / OutSize.x;
    float onePixelSizeY = 1.0 / OutSize.y;

    float cellSizeX = 10.0 * onePixelSizeX;
    float cellSizeY = 10.0 * onePixelSizeY;

    float x = floor( texCoord.x / cellSizeX ) * cellSizeX;
    float y = floor( texCoord.y / cellSizeY ) * cellSizeY;

//    vec2 pixelTexCoord = texCoord;
    vec2 pixelTexCoord = vec2(x, y);


    vec4 outColor = texture(DiffuseSampler, pixelTexCoord) * ColorModulate;
    fragColor = outColor;
}
