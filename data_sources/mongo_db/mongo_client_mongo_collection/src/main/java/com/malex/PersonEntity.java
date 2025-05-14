package com.malex;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;

public record PersonEntity(
    @JsonSerialize(using = ToStringSerializer.class) ObjectId id, String name, Integer age) {}
