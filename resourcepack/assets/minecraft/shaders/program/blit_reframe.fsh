#version 150

uniform sampler2D DiffuseSampler;

uniform vec4 ColorModulate;

uniform vec2 OutSize;

in vec2 texCoord;

out vec4 fragColor;

void main() {
    vec4 outColor = texture(DiffuseSampler, texCoord) * ColorModulate;
    vec2 fragPos = vec2(gl_FragCoord.x / OutSize.x, gl_FragCoord.y / OutSize.y);
//    fragColor = vec4(1.0);
    if (fragPos.x < 0.2) {
        fragColor = vec4(1.0, 0.0, 0.0, 1.0);
    } else {
        fragColor = outColor;
    }
}
