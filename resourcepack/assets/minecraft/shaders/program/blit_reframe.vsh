#version 150

in vec4 Position;

uniform mat4 ProjMat;
uniform vec2 OutSize;

out vec2 texCoord;

void main(){
    vec4 outPos = ProjMat * vec4(Position.x + 5, Position.y, 0.0, 1.0);
    vec2 stretched = mat2(0.5, 0, 0, 0.5) * outPos.xy;
    gl_Position = vec4(stretched, 0.2, 1.0);

    texCoord = Position.xy / OutSize;
}
