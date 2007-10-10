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
package org.eclipse.net4j.buddies.internal.ui.bundle;

import org.eclipse.net4j.buddies.internal.ui.BuddiesManager;
import org.eclipse.net4j.util.om.OMBundle;
import org.eclipse.net4j.util.om.OMPlatform;
import org.eclipse.net4j.util.om.log.OMLogger;
import org.eclipse.net4j.util.om.pref.OMPreference;
import org.eclipse.net4j.util.om.pref.OMPreferences;
import org.eclipse.net4j.util.om.trace.OMTracer;
import org.eclipse.net4j.util.ui.UIActivator;

/**
 * @author Eike Stepper
 */
public abstract class OM
{
  public static final String BUNDLE_ID = "org.eclipse.net4j.buddies.ui"; //$NON-NLS-1$

  public static final OMBundle BUNDLE = OMPlatform.INSTANCE.bundle(BUNDLE_ID, OM.class);

  public static final OMTracer DEBUG = BUNDLE.tracer("debug"); //$NON-NLS-1$

  public static final OMLogger LOG = BUNDLE.logger();

  public static final OMPreferences PREFS = BUNDLE.preferences();

  public static final OMPreference<String> PREF_CONNECTOR_DESCRIPTION = // 
  PREFS.init("PREF_CONNECTOR_DESCRIPTION", "tcp://localhost"); //$NON-NLS-1$

  public static final OMPreference<String> PREF_USER_ID = // 
  PREFS.init("PREF_USER_ID", System.getProperty("user.name")); //$NON-NLS-1$

  public static final OMPreference<String> PREF_PASSWORD = // 
  PREFS.initString("PREF_PASSWORD"); //$NON-NLS-1$

  public static final OMPreference<Boolean> PREF_AUTO_CONNECT = // 
  PREFS.init("PREF_AUTO_CONNECT", true); //$NON-NLS-1$

  static void start() throws Exception
  {
    BuddiesManager.INSTANCE.activate();
  }

  static void stop() throws Exception
  {
    BuddiesManager.INSTANCE.deactivate();
  }

  /**
   * @author Eike Stepper
   */
  public static final class Activator extends UIActivator
  {
    public static Activator INSTANCE;

    public Activator()
    {
      super(BUNDLE);
      INSTANCE = this;
    }
  }
}
