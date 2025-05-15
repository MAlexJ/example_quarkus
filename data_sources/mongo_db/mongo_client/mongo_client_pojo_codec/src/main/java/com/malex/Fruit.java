package com.malex;

import org.bson.types.ObjectId;

public record Fruit(ObjectId id, String name, String description) {}
