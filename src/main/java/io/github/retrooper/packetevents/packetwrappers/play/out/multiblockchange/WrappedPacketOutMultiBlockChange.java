/*
 * MIT License
 *
 * Copyright (c) 2020 retrooper
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.retrooper.packetevents.packetwrappers.play.out.multiblockchange;

import io.github.retrooper.packetevents.packetwrappers.NMSPacket;
import io.github.retrooper.packetevents.packetwrappers.WrappedPacket;
//TODO finish this wrapper
class WrappedPacketOutMultiBlockChange extends WrappedPacket {

    public WrappedPacketOutMultiBlockChange(NMSPacket packet) {
        super(packet);
    }

    @Override
    protected void load() {
        net.minecraft.server.v1_7_R4.PacketPlayOutMultiBlockChange a0;
        net.minecraft.server.v1_8_R3.PacketPlayOutMultiBlockChange a1;
        net.minecraft.server.v1_9_R1.PacketPlayOutMultiBlockChange a2;
        net.minecraft.server.v1_9_R2.PacketPlayOutMultiBlockChange a3;
        net.minecraft.server.v1_12_R1.PacketPlayOutMultiBlockChange a4;
        net.minecraft.server.v1_13_R1.PacketPlayOutMultiBlockChange a5;
        net.minecraft.server.v1_13_R2.PacketPlayOutMultiBlockChange a6;
        net.minecraft.server.v1_16_R2.PacketPlayOutMultiBlockChange a7;

    }


}