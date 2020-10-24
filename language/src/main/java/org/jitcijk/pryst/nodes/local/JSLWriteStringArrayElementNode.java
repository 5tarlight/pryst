package com.BScProject.truffle.jsl.nodes.local;

import com.BScProject.truffle.jsl.nodes.JSLType;
import com.BScProject.truffle.jsl.nodes.JSLTypedExpressionNode;
import com.BScProject.truffle.jsl.runtime.JSLRuntimeException;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeChildren({@NodeChild("array"), @NodeChild("index"), @NodeChild("value")})
@NodeInfo(shortName="=")
public abstract class JSLWriteStringArrayElementNode extends JSLTypedExpressionNode {
	@Specialization
	protected String writeStringArrayElement(String[] array, long index, String value) {
		try {
			array[(int) index] = value;
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new JSLRuntimeException(this, "index "+index+" out of bounds");
		}
		return value;
	}
	
	@Override
	public JSLType getType() {
		return JSLType.STRING;
	}
}