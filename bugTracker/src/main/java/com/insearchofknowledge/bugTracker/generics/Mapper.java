package com.insearchofknowledge.bugTracker.generics;

public interface Mapper<StartingObject, Result> {

    public Result map(StartingObject startingObject);
}
