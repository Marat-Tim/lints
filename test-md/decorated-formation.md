# Decorated formation

A formation shouldn't be used as a decoratee. It means that abstract object
should not have `@` as its name.

Incorrect:

```eo
[] > main
  [] > @
    hello > test
```

Correct:

```eo
[] > main
  [] > foo
    hello > test
```
