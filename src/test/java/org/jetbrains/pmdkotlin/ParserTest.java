package org.jetbrains.pmdkotlin;

import net.sourceforge.pmd.lang.ParserOptions;
import org.jetbrains.kotlin.psi.JetPackageDirective;
import org.jetbrains.pmdkotlin.lang.kotlin.KotlinParser;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.AbstractKotlinNode;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinParserVisitorAdapter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;

@Test
public class ParserTest {

    private KotlinParser parser = new KotlinParser(new ParserOptions());

    @Test
    public void testEmptyInput() throws Exception {
        File file = writeTempFile("");

        parser.parse(file.getAbsolutePath(), new FileReader(file));
    }

    @Test
    public void testPackageDirective() throws Exception {
        File file = writeTempFile("package org.jetbrains.pmdkotlin");
        KotlinParserVisitorAdapter adapter = new KotlinParserVisitorAdapter(){
            @Override
            public Object visitPackageDirectivePMD(JetPackageDirective directive, Object data) {
                Assert.assertEquals(directive.getText(), "package org.jetbrains.pmdkotlin");
                return super.visitPackageDirectivePMD(directive, data);
            }
        };

        AbstractKotlinNode root = parser.parse(file.getAbsolutePath(), new FileReader(file));
        root.jjtAccept(adapter, null);
    }

    private File writeTempFile(String content){
        try {
            File file = File.createTempFile("prefix", ".kt");
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}