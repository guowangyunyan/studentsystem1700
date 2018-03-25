package com.training.convert;

public interface Convert<K, V> {

	V createTarget();

	V convert(K source);
}
