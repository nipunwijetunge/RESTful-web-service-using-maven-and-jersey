package com.nipun.abxPackageDeliveryService.patch_implementation;

public interface ObjectPatch {
	<T> T apply(T target) throws ObjectPatchException;
}
