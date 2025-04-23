# Empty `+spdx` Tail

The special meta attribute `+spdx` cannot have an empty tail.

Incorrect:

```eo
+spdx

# Foo.
[] > foo
```

Correct:

```eo
+spdx foo

# Foo.
[] > foo
```
