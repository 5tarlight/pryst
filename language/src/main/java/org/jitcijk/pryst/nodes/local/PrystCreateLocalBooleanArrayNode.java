package org.jitcijk.pryst.nodes.local;

import org.jitcijk.pryst.nodes.PrystExpressionNode;
import org.jitcijk.pryst.nodes.PrystStatementNode;
import org.jitcijk.pryst.runtime.PrystRuntimeException;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

public class PrystCreateLocalBooleanArrayNode extends PrystStatementNode {
	private final FrameSlot frameSlot;
	@Child PrystExpressionNode size;
	
	public PrystCreateLocalBooleanArrayNode(FrameSlot frameSlot, PrystExpressionNode size) {
		this.frameSlot = frameSlot;
		this.size = size;
	}
	
	@Override
	public void executeVoid(VirtualFrame frame) {
		int s;
		try {
			s = (int) size.executeLong(frame);
		} catch (UnexpectedResultException ex) {
			throw new PrystRuntimeException(this, ex);
		}
		
		frame.setObject(frameSlot, new boolean[s]);
	}
}