package com.beta.crop.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class BufferUtil {

	public BufferUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static ByteBuf str2ByteBuf(String content){
		return Unpooled.copiedBuffer(content.getBytes());
	}

}
