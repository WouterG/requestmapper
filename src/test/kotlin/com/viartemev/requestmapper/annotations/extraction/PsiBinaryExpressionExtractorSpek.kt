package com.viartemev.requestmapper.annotations.extraction

import com.intellij.psi.PsiBinaryExpression
import com.intellij.psi.PsiLiteralExpression
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldEqualTo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object PsiBinaryExpressionExtractorSpek : Spek({

    describe("PsiBinaryExpressionExtractor") {
        on("extract with 2 PsiLiteralExpressions") {
            it("should return list with sum of literal expressions texts") {
                val literalExpression = mock<PsiLiteralExpression> {
                    on { text } doReturn "api"
                }
                val psiBinaryExpression = mock<PsiBinaryExpression> {
                    on { lOperand } doReturn literalExpression
                    on { rOperand } doReturn literalExpression
                }
                val extract = PsiBinaryExpressionExtractor().extract(psiBinaryExpression)
                extract.size shouldEqualTo 1
                extract shouldContain "apiapi"
            }
        }
    }
})
