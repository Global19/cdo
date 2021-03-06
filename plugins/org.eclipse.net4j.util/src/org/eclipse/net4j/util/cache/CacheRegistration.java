/*
 * Copyright (c) 2008, 2009, 2011, 2012, 2015, 2019 Eike Stepper (Loehne, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.net4j.util.cache;

/**
 * @author Eike Stepper
 */
public class CacheRegistration implements ICacheRegistration
{
  public static final float DEFAULT_RECONSTRUCTION_COST_DECAY_FACTOR = 0.1f;

  private ICacheMonitor cacheMonitor;

  private ICache cache;

  private int elementCount;

  private long cacheSize;

  private long reconstructionCost;

  public CacheRegistration(ICacheMonitor cacheMonitor, ICache cache)
  {
    this.cacheMonitor = cacheMonitor;
    this.cache = cache;
  }

  @Override
  public void dispose()
  {
    cacheMonitor = null;
    cache = null;
  }

  @Override
  public boolean isDisposed()
  {
    return cacheMonitor == null || cache == null;
  }

  @Override
  public ICacheMonitor getCacheMonitor()
  {
    return cacheMonitor;
  }

  @Override
  public ICache getCache()
  {
    return cache;
  }

  @Override
  public int getElementCount()
  {
    return elementCount;
  }

  @Override
  public long getCacheSize()
  {
    return cacheSize;
  }

  @Override
  public long getAverageElementSize()
  {
    return cacheSize / elementCount;
  }

  @Override
  public long getReconstructionCost()
  {
    return reconstructionCost;
  }

  @Override
  public void elementCached(int elementSize)
  {
    checkDisposal();
    ++elementCount;
    cacheSize += elementSize;
  }

  @Override
  public void elementEvicted(int elementSize)
  {
    checkDisposal();
    --elementCount;
    cacheSize -= elementSize;
  }

  @Override
  public void elementReconstructed(long reconstructionTime)
  {
    checkDisposal();
    float decayFactor = getReconstructionCostDecayFactor();
    reconstructionCost = (long)(decayFactor * reconstructionCost + (1 - decayFactor) * reconstructionTime);
  }

  protected float getReconstructionCostDecayFactor()
  {
    return DEFAULT_RECONSTRUCTION_COST_DECAY_FACTOR;
  }

  private void checkDisposal()
  {
    if (isDisposed())
    {
      throw new IllegalStateException("disposed"); //$NON-NLS-1$
    }
  }
}
