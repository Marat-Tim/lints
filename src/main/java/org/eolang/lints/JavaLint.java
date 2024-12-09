package org.eolang.lints;

import java.io.IOException;
import java.util.Collection;

import com.jcabi.xml.XML;
import io.github.secretx33.resourceresolver.PathMatchingResourcePatternResolver;
import org.cactoos.text.FormattedText;

public class JavaLint implements Lint<XML> {
    private final Lint<XML> lint;

    public JavaLint(Lint<XML> lint) {
        this.lint = lint;
    }

    @Override
    public Collection<Defect> defects(XML entity) throws IOException {
        return lint.defects(entity);
    }

    @Override
    public String motive() throws Exception {
        String className = lint.getClass()
                .getSimpleName()
                .replaceAll("([a-z0-9])([A-Z])", "$1-$2")
                .toLowerCase();
        String packageName = lint.getClass().getPackage().getName();
        packageName = packageName.substring(packageName.lastIndexOf(".") + 1);
        return new PathMatchingResourcePatternResolver().getResources(
                new FormattedText("classpath*:org/eolang/motives/%s/%s.md", packageName, className).asString()
        )[0].getURL().toString();
    }

}
