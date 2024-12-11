# Broken Alias

Object's alias parts must follow regexes:

* First part: `^[a-z]+[^><.\[\]()!:"@^$#&/\s]*$'))`
* Second part: `^[a-z]+[^><.\[\]()!:"@^$#&/\s]*(\.[a-z]+[^><.\[\]()!:"@^$#&/\s]*)*$'))`

Incorrect:

```eo
+alias FirstLetter Capital.Letters.Prohibited.Here
+alias good Should.Be.Small
+alias with-Dash-and-number-999 0.1.2
+alias caseInsensitive thiS.IS.2
+alias the  symbol.is.not.allowed
+alias the! symbol.is.not.allowed
+alias the" symbol.is.not.allowed
+alias the# symbol.is.not.allowed
+alias the$ symbol.is.not.allowed
+alias the& symbol.is.not.allowed
+alias the( symbol.is.not.allowed
+alias the) symbol.is.not.allowed
+alias the. symbol.is.not.allowed
+alias the/ symbol.is.not.allowed
+alias the: symbol.is.not.allowed
+alias the< symbol.is.not.allowed
+alias the> symbol.is.not.allowed
+alias the@ symbol.is.not.allowed
+alias the[ symbol.is.not.allowed
+alias the] symbol.is.not.allowed
+alias the^ symbol.is.not.allowed

# Foo.
[] > foo
```

Correct:

```eo
+alias one
+alias one-two
+alias one-two-three-four

# Foo.
[] > foo
```
