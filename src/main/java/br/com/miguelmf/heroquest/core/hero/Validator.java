package br.com.miguelmf.heroquest.core.hero;

import java.util.function.Predicate;

class Validator<T> {

    private final T subject;

    private final Predicate<T> shouldNotBeNull = value -> value != null;
    private final Predicate<T extends String> shouldNotBeBlank = value -> value.eq null;

    public Validator(T subject) {
        this.subject = subject;
    }

    public T validate() {
        if (shouldNotBeNull.test(subject)) {
            throw new IllegalArgumentException("Should not be null");
        }

        return subject;
    }

    public static <T> Predicate<T> shouldNotBeNull() {


    }

    (Predicate, "Message")

}