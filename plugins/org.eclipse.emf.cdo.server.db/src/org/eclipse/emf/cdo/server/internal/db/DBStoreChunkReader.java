/**
 * Copyright (c) 2004 - 2009 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.emf.cdo.server.internal.db;

import org.eclipse.emf.cdo.common.revision.CDORevision;
import org.eclipse.emf.cdo.server.db.IDBStoreChunkReader;
import org.eclipse.emf.cdo.server.db.mapping.IClassMapping;
import org.eclipse.emf.cdo.server.db.mapping.IMappingStrategy;
import org.eclipse.emf.cdo.server.db.mapping.IReferenceMapping;
import org.eclipse.emf.cdo.spi.server.StoreChunkReader;

import org.eclipse.emf.ecore.EStructuralFeature;

import java.util.List;

/**
 * @author Eike Stepper
 */
public class DBStoreChunkReader extends StoreChunkReader implements IDBStoreChunkReader
{
  private IReferenceMapping referenceMapping;

  private StringBuilder builder = new StringBuilder();

  public DBStoreChunkReader(DBStoreAccessor accessor, CDORevision revision, EStructuralFeature feature)
  {
    super(accessor, revision, feature);
    IMappingStrategy mappingStrategy = accessor.getStore().getMappingStrategy();
    IClassMapping mapping = mappingStrategy.getClassMapping(revision.getEClass());
    referenceMapping = mapping.getReferenceMapping(feature);
  }

  @Override
  public DBStoreAccessor getAccessor()
  {
    return (DBStoreAccessor)super.getAccessor();
  }

  @Override
  public void addSimpleChunk(int index)
  {
    super.addSimpleChunk(index);
    builder.append(" AND ");
    builder.append(CDODBSchema.REFERENCES_IDX);
    builder.append("=");
    builder.append(index);
  }

  @Override
  public void addRangedChunk(int fromIndex, int toIndex)
  {
    super.addRangedChunk(fromIndex, toIndex);
    builder.append(" AND ");
    builder.append(CDODBSchema.REFERENCES_IDX);
    builder.append(" BETWEEN ");
    builder.append(fromIndex);
    builder.append(" AND ");
    builder.append(toIndex - 1);
  }

  public List<Chunk> executeRead()
  {
    List<Chunk> chunks = getChunks();
    referenceMapping.readChunks(this, chunks, builder.toString());
    return chunks;
  }
}
