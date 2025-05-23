package com.malex;

import io.quarkus.runtime.annotations.RegisterForReflection;

/*
 * The @RegisterForReflection annotation in Quarkus is used to tell the native image compiler (GraalVM)
 * to include specific classes for reflection at runtime.
 *
 * It marks a class (and optionally its methods, fields, and constructors)
 * to be available for reflection in a native image.
 */
@RegisterForReflection
public record Quote(String id, int price) {}
