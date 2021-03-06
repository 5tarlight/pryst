/*
 * The MIT License
 *
 * Copyright (c) 2020 Jitcijk Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jitcijk.pryst.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.Fallback;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.jitcijk.pryst.PrystException;
import org.jitcijk.pryst.nodes.PrystBinaryNode;
import org.jitcijk.pryst.runtime.PrystBigInteger;

/**
 * This class is similar to the extensively documented {@link PrystAddNode}.
 */
@NodeInfo(shortName = "*")
public abstract class PrystMulNode extends PrystBinaryNode {

    @Specialization(rewriteOn = ArithmeticException.class)
    protected long mul(long left, long right) {
        return Math.multiplyExact(left, right);
    }

    @Specialization(rewriteOn = ArithmeticException.class)
    protected double mul(double left, double right) {
        return left * right;
    }

    @Specialization
    @TruffleBoundary
    protected PrystBigInteger mul(PrystBigInteger left, PrystBigInteger right) {
        return new PrystBigInteger(left.getValue().multiply(right.getValue()));
    }

    @Fallback
    protected Object typeError(Object left, Object right) {
        throw PrystException.typeError(this, left, right);
    }

}
