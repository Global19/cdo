/***************************************************************************
 * Copyright (c) 2004 - 2008 Eike Stepper, Germany.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Simon McDuff - initial API and implementation
 **************************************************************************/
package org.eclipse.emf.cdo.util;

import org.eclipse.emf.cdo.CDOSession;
import org.eclipse.emf.cdo.CDOView;
import org.eclipse.emf.cdo.common.CDOProtocolConstants;
import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.cdo.common.id.CDOIDUtil;
import org.eclipse.emf.cdo.internal.common.id.CDOIDExternalImpl;

import org.eclipse.emf.common.util.URI;

/**
 * @author Simon McDuff
 * @since 2.0
 */
public class CDOURIUtil
{
  private static final char SEGMENT_SEPARATOR = '/';

  public static boolean validateURI(URI uri)
  {
    if (!CDOProtocolConstants.PROTOCOL_NAME.equals(uri.scheme()))
    {
      return false;
    }

    if (!uri.isHierarchical())
    {
      return false;
    }

    if (!uri.hasAbsolutePath())
    {
      return false;
    }
    return true;
  }

  public static String extractResourcePath(URI uri)
  {
    if (!validateURI(uri))
    {
      return null;
    }
    return uri.path();
  }

  public static String extractRepositoryUUID(URI uri)
  {
    if (!validateURI(uri) || !uri.hasAuthority())
    {
      return null;
    }
    return uri.authority();
  }

  /**
   * <p>
   * cdo://repositoryUUID/path
   * <p>
   * The path is added at the end of "cdo://repositoryUUID". If path doesn't start with '/', it will be added
   * automatically. <br>
   * e.g.: /resA or resA will give the same result -> cdo://repositoryUUID/resA <br>
   * authority = repositoryUUID <br>
   * path = /resA
   */
  public static URI createResourceURI(String repositoryUUID, String path)
  {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(CDOProtocolConstants.PROTOCOL_NAME);
    stringBuilder.append(":");

    if (repositoryUUID != null)
    {
      stringBuilder.append("//");
      stringBuilder.append(repositoryUUID);
    }

    if (path.charAt(0) != SEGMENT_SEPARATOR)
    {
      stringBuilder.append(SEGMENT_SEPARATOR);
    }
    stringBuilder.append(path);
    return URI.createURI(stringBuilder.toString());
  }

  public static URI createResourceURI(CDOView cdoView, String path)
  {
    return createResourceURI(cdoView == null ? null : cdoView.getSession(), path);
  }

  public static URI createResourceURI(CDOSession cdoSession, String path)
  {
    return createResourceURI(cdoSession == null ? null : cdoSession.getRepositoryUUID(), path);
  }

  /**
   * Converting temporary CDOID to External CDOID <br>
   * e.g.: <br>
   * baseURI = cdo://2a57dfcf-8f97-4d39-8e17-9d99ae5c4b3c/resB#5/2<br>
   * newCDOID = OID2<br>
   * return = cdo://2a57dfcf-8f97-4d39-8e17-9d99ae5c4b3c/resB#1/2
   */
  public static CDOID convertExternalCDOID(URI baseURI, CDOID newCDOID)
  {
    baseURI = baseURI.trimFragment();

    StringBuilder builder = new StringBuilder();

    CDOIDUtil.write(builder, newCDOID);
    baseURI = baseURI.appendFragment(builder.toString());

    return new CDOIDExternalImpl(baseURI.toString());
  }
}
