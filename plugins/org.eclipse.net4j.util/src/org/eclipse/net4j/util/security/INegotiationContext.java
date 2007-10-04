/***************************************************************************
 * Copyright (c) 2004 - 2007 Eike Stepper, Germany.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Eike Stepper - initial API and implementation
 **************************************************************************/
package org.eclipse.net4j.util.security;

import java.nio.ByteBuffer;

/**
 * @author Eike Stepper
 */
public interface INegotiationContext
{
  public ByteBuffer getBuffer();

  public void transmitBuffer(ByteBuffer buffer);

  public void setBufferReceiver(IBufferReceiver receiver);

  public void negotiationSuccess();

  public void negotiationFailure();

  /**
   * @author Eike Stepper
   */
  public interface Receiver
  {
    public void receiveBuffer(INegotiationContext context, ByteBuffer buffer);
  }
}
