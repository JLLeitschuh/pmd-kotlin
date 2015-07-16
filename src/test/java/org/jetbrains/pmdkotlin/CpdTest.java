package org.jetbrains.pmdkotlin;

import net.sourceforge.pmd.cpd.*;
import org.jetbrains.pmdkotlin.cpd.KotlinLanguage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.mockito.Mockito.mock;

public class CpdTest {

    private CPD cpd;
    private CPDConfiguration config;
    private CPDListener listener;
    private Map<String, String> hm;

    @BeforeTest
    public void setUp() {
        config = new CPDConfiguration();
        config.setLanguage(new KotlinLanguage());
        config.setEncoding("UTF-8");
        config.setMinimumTileSize(10);
        config.setIgnoreIdentifiers(true);
        config.setIgnoreLiterals(true);
        cpd = new CPD(config);
        listener = mock(CPDListener.class);
        cpd.setCpdListener(listener);
    }

    @Test
    public void testDuplicateFunction() throws IOException {
        cpd.add(getKotlinFile("DuplicateFunction.kt"));
        cpd.go();

        show(cpd.getMatches());
    }

//    @Test
//    public void testIgnoreImports() throws IOException {
//        cpd.add(getKotlinFile("ignoreImportsTest/IgnoreImports1.kt"));
//        cpd.add(getKotlinFile("ignoreImportsTest/IgnoreImports2.kt"));
//
//        cpd.go();
//
//        show(cpd.getMatches());
//    }

    @Test
    public void testIgnoreIdentifiersAndLiterals() throws IOException {
        cpd.add(getKotlinFile("ignoreIdentifiersAndLiteralsTest/IgnoreIdentifiers.kt"));
        cpd.add(getKotlinFile("ignoreIdentifiersAndLiteralsTest/IgnoreIdentifiers2.kt"));

        cpd.go();

        show(cpd.getMatches());
    }

    public void show(Iterator<Match> matches) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("test_results.txt");
        hm = new HashMap<>();
        for (SourceCode sc : cpd.getSources()) {
            hm.put(sc.getFileName(), sc.getCodeBuffer().toString());
        }

        while (matches.hasNext()) {
            Match match = matches.next();
            for (Mark m : match.getMarkSet()) {
                m.setSoureCodeSlice(hm.get(m.getFilename()).substring(m.getBeginLine(), m.getEndLine() + 1));
                pw.println(m.getFilename());
                pw.println(m.getSourceCodeSlice());
            }
            pw.println();
            pw.println();
        }
        pw.close();

    }

    private File getKotlinFile(String name) {
        try {
            return new File(getClass().getResource(name).toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Can't find " + name, e);
        }
    }
}
