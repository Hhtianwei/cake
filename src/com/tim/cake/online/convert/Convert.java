package com.tim.cake.online.convert;

public interface Convert<SOURCE, TARGET>
{
	void convert(SOURCE source, TARGET target);
}
