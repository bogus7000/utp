package com.brodskyi.assignment01.api;

public interface IAggregable<TElement extends IAggregable<TElement, TResult>, TResult> {

    TResult aggregate(TResult intermediateResult);
}
