# Error Line Out Of Listing

Line number inside `<error/>` in [XMIR] cannot be bigger than amount of lines
in the listing.

Incorrect:

```xml
<program>
  <listing>first line
    second line
    third line</listing>
  <errors>
    <error check="abc" line="500" severity="warning">hello</error>
  </errors>
</program>
```

Correct:

```xml
<program>
  <listing>first line
    second line
    third line</listing>
  <errors>
    <error check="abc" line="3" severity="warning">hello</error>
  </errors>
</program>
```

[XMIR]: https://news.eolang.org/2022-11-25-xmir-guide.html
