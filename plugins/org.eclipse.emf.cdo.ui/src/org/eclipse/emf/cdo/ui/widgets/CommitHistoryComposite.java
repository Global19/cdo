/*
 * Copyright (c) 2012, 2013 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.emf.cdo.ui.widgets;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.cdo.CDOState;
import org.eclipse.emf.cdo.common.branch.CDOBranch;
import org.eclipse.emf.cdo.common.commit.CDOCommitHistory;
import org.eclipse.emf.cdo.common.commit.CDOCommitHistory.TriggerLoadElement;
import org.eclipse.emf.cdo.common.commit.CDOCommitInfo;
import org.eclipse.emf.cdo.common.commit.CDOCommitInfoHandler;
import org.eclipse.emf.cdo.common.commit.CDOCommitInfoManager;
import org.eclipse.emf.cdo.common.util.CDOCommonUtil;
import org.eclipse.emf.cdo.internal.ui.history.NetRenderer;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.spi.common.branch.InternalCDOBranchManager;
import org.eclipse.emf.cdo.ui.shared.SharedIcons;
import org.eclipse.emf.cdo.util.CDOUtil;
import org.eclipse.emf.cdo.view.CDOView;

import org.eclipse.net4j.util.AdapterUtil;
import org.eclipse.net4j.util.ObjectUtil;
import org.eclipse.net4j.util.StringUtil;
import org.eclipse.net4j.util.container.ContainerEventAdapter;
import org.eclipse.net4j.util.container.IContainer;
import org.eclipse.net4j.util.event.EventUtil;
import org.eclipse.net4j.util.event.IEvent;
import org.eclipse.net4j.util.event.IListener;
import org.eclipse.net4j.util.event.Notifier;
import org.eclipse.net4j.util.lifecycle.ILifecycle;
import org.eclipse.net4j.util.lifecycle.ILifecycleEvent;
import org.eclipse.net4j.util.lifecycle.ILifecycleEvent.Kind;
import org.eclipse.net4j.util.lifecycle.LifecycleEvent;
import org.eclipse.net4j.util.lifecycle.LifecycleException;
import org.eclipse.net4j.util.lifecycle.LifecycleState;
import org.eclipse.net4j.util.lifecycle.LifecycleUtil;
import org.eclipse.net4j.util.registry.IRegistry;
import org.eclipse.net4j.util.ui.StructuredContentProvider;
import org.eclipse.net4j.util.ui.TableLabelProvider;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.spi.cdo.CDOSessionProtocol;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

/**
 * A UI component that renders a the elements of a {@link CDOCommitHistory} in form of a commit table with a branch tree.
 *
 * @author Eike Stepper
 * @since 4.2
 */
public class CommitHistoryComposite extends Composite
{
  private CDOCommitHistory history;

  private IListener historyListener = new ContainerEventAdapter<CDOCommitInfo>()
  {
    @Override
    protected void onAdded(IContainer<CDOCommitInfo> history, CDOCommitInfo commitInfo)
    {
      netRenderer.addCommit(commitInfo);
    }
  };

  private int viewerStyle;

  private TableViewer tableViewer;

  private LabelProvider labelProvider;

  private NetRenderer netRenderer;

  private Input input;

  public CommitHistoryComposite(Composite parent, int viewerStyle)
  {
    super(parent, SWT.NONE);
    this.viewerStyle = viewerStyle;

    setLayout(new FillLayout(SWT.HORIZONTAL));

    tableViewer = createTableViewer();
    tableViewer.setContentProvider(createContentProvider());

    tableViewer.addSelectionChangedListener(new ISelectionChangedListener()
    {
      public void selectionChanged(SelectionChangedEvent event)
      {
        IStructuredSelection selection = (IStructuredSelection)tableViewer.getSelection();
        Object selectedElement = selection.getFirstElement();
        if (selectedElement instanceof CDOCommitInfo)
        {
          commitInfoChanged((CDOCommitInfo)selectedElement);
        }
      }
    });

    tableViewer.addDoubleClickListener(new IDoubleClickListener()
    {
      public void doubleClick(DoubleClickEvent event)
      {
        doubleClicked();
      }
    });

    tableViewer.addOpenListener(new IOpenListener()
    {
      public void open(OpenEvent event)
      {
        doubleClicked();
      }
    });

    labelProvider = createLabelProvider();
    labelProvider.support(tableViewer);

    netRenderer = new NetRenderer(tableViewer);
  }

  /**
   * @since 4.4
   */
  public final int getViewerStyle()
  {
    return viewerStyle;
  }

  public final TableViewer getTableViewer()
  {
    return tableViewer;
  }

  public final LabelProvider getLabelProvider()
  {
    return labelProvider;
  }

  public final Input getInput()
  {
    return input;
  }

  public final void setInput(Input input)
  {
    if (!ObjectUtil.equals(this.input, input))
    {
      this.input = input;

      CDOCommitHistory oldHistory = history;
      if (input == null)
      {
        history = CDOCommitHistory.EMPTY;
      }
      else
      {
        CDOSession session = input.getSession();
        CDOBranch branch = input.getBranch();
        CDOObject object = input.getObject();

        labelProvider.setLocalUserID(session.getUserID());
        labelProvider.setInputBranch(branch);

        history = createHistory(session, branch, object);
        history.setAppendingTriggerLoadElement(true);
      }

      history.addListener(historyListener);
      refreshLayout();

      if (oldHistory != null && oldHistory != history)
      {
        oldHistory.removeListener(historyListener);
        LifecycleUtil.deactivate(oldHistory);
      }
    }
  }

  public void refreshLayout()
  {
    netRenderer.setInput(input);
    CDOCommitInfo[] elements = history.getElements();
    for (int i = 0; i < elements.length; i++)
    {
      CDOCommitInfo commitInfo = elements[i];
      netRenderer.addCommit(commitInfo);
    }

    Table table = tableViewer.getTable();
    table.setTopIndex(0);
    tableViewer.setInput(history);
    table.setTopIndex(0);
  }

  public final CDOCommitHistory getHistory()
  {
    return history;
  }

  @Override
  public boolean setFocus()
  {
    return tableViewer.getTable().setFocus();
  }

  @Override
  public void dispose()
  {
    input = null;
    history = null;
    super.dispose();
  }

  protected TableViewer createTableViewer()
  {
    return new TableViewer(this, getViewerStyle() | SWT.FULL_SELECTION | SWT.MULTI);
  }

  protected ContentProvider createContentProvider()
  {
    return new ContentProvider();
  }

  protected LabelProvider createLabelProvider()
  {
    return new LabelProvider();
  }

  protected CDOCommitHistory createHistory(CDOSession session, CDOBranch branch, CDOObject object)
  {
    if (object == null)
    {
      CDOCommitInfoManager commitInfoManager = session.getCommitInfoManager();
      return commitInfoManager.getHistory(branch);
    }

    return object.cdoHistory();
  }

  protected void commitInfoChanged(CDOCommitInfo newCommitInfo)
  {
  }

  protected void doubleClicked(CDOCommitInfo commitInfo)
  {
  }

  private void doubleClicked()
  {
    IStructuredSelection selection = (IStructuredSelection)tableViewer.getSelection();
    CDOCommitInfo commitInfo = (CDOCommitInfo)selection.getFirstElement();
    if (commitInfo != null)
    {
      if (commitInfo instanceof TriggerLoadElement)
      {
        history.triggerLoad(new RevealElementHandler());
      }
      else
      {
        doubleClicked(commitInfo);
      }
    }
  }

  /**
   * @author Eike Stepper
   */
  private final class RevealElementHandler implements CDOCommitInfoHandler
  {
    public void handleCommitInfo(final CDOCommitInfo commitInfo)
    {
      getDisplay().asyncExec(new Runnable()
      {
        public void run()
        {
          tableViewer.reveal(commitInfo);
        }
      });
    }
  }

  /**
   * Encapsulates the input of a {@link CommitHistoryComposite}.
   *
   * @author Eike Stepper
   */
  public static class Input extends Notifier implements ILifecycle
  {
    private IListener lifecycleListener = new IListener()
    {
      public void notifyEvent(IEvent event)
      {
        if (event instanceof ILifecycleEvent)
        {
          Kind kind = ((ILifecycleEvent)event).getKind();
          if (kind == Kind.DEACTIVATED)
          {
            deactivate();
          }

          fireEvent(new LifecycleEvent(Input.this, kind));
        }
      }
    };

    private final CDOSession session;

    private final CDOBranch branch;

    private final CDOObject object;

    private final boolean offline;

    public Input(Object delegate) throws IllegalInputException
    {
      CDOSession sessionAdapter = AdapterUtil.adapt(delegate, CDOSession.class);
      if (sessionAdapter != null)
      {
        session = sessionAdapter;
        offline = determineOffline();
        branch = null;
        object = null;
        return;
      }

      CDOBranch branchAdapter = AdapterUtil.adapt(delegate, CDOBranch.class);
      if (branchAdapter != null)
      {
        branch = branchAdapter;
        session = (CDOSession)((CDOSessionProtocol)((InternalCDOBranchManager)branch.getBranchManager())
            .getBranchLoader()).getSession();
        offline = determineOffline();
        object = null;
        return;
      }

      CDOView viewAdapter = AdapterUtil.adapt(delegate, CDOView.class);
      if (viewAdapter != null)
      {
        CDOView view = viewAdapter;
        session = view.getSession();
        offline = determineOffline();
        branch = offline ? null : view.getBranch();
        object = null;
        return;
      }

      if (delegate instanceof EObject)
      {
        EObject eObject = (EObject)delegate;
        CDOObject cdoObject = CDOUtil.getCDOObject(eObject);
        if (cdoObject != null)
        {
          CDOView view = cdoObject.cdoView();
          if (view != null && cdoObject.cdoState() != CDOState.NEW)
          {
            session = view.getSession();
            offline = determineOffline();
            branch = offline ? null : view.getBranch();
            object = offline ? null : cdoObject;
            return;
          }
        }
      }

      throw new IllegalInputException("Illegal input: " + delegate);
    }

    public Input(CDOSession session, CDOBranch branch, CDOObject object)
    {
      this.session = session;
      offline = determineOffline();
      this.branch = offline ? null : branch;
      this.object = offline ? null : object;
    }

    public final CDOSession getSession()
    {
      return session;
    }

    public final CDOBranch getBranch()
    {
      return branch;
    }

    public final CDOObject getObject()
    {
      return object;
    }

    /**
     * @since 4.4
     */
    public final boolean isOffline()
    {
      return offline;
    }

    @Override
    public int hashCode()
    {
      final int prime = 31;
      int result = 1;
      result = prime * result + (session == null ? 0 : session.hashCode());
      result = prime * result + (branch == null ? 0 : branch.hashCode());
      result = prime * result + (object == null ? 0 : object.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj)
    {
      if (this == obj)
      {
        return true;
      }

      if (obj == null)
      {
        return false;
      }

      if (!(obj instanceof Input))
      {
        return false;
      }

      Input other = (Input)obj;
      if (session == null)
      {
        if (other.session != null)
        {
          return false;
        }
      }
      else if (!session.equals(other.session))
      {
        return false;
      }

      if (branch == null)
      {
        if (other.branch != null)
        {
          return false;
        }
      }
      else if (!branch.equals(other.branch))
      {
        return false;
      }

      return object == other.object;
    }

    @Override
    public String toString()
    {
      if (offline)
      {
        return "Offline: " + session;
      }

      String string = "Repository: " + session.getRepositoryInfo().getName() + ", Branch: "
          + (branch != null ? branch.getPathName() : CDOBranch.MAIN_BRANCH_NAME);

      if (object != null)
      {
        string += ", Object: " + object;
      }

      return string;
    }

    public void activate() throws LifecycleException
    {
      EventUtil.addListener(getLifecycle(), lifecycleListener);
    }

    public Exception deactivate()
    {
      EventUtil.removeListener(getLifecycle(), lifecycleListener);
      return null;
    }

    public LifecycleState getLifecycleState()
    {
      Object object = getLifecycle();
      return LifecycleUtil.getLifecycleState(object);
    }

    public boolean isActive()
    {
      Object object = getLifecycle();
      return LifecycleUtil.isActive(object);
    }

    protected final Object getLifecycle()
    {
      if (offline)
      {
        return null;
      }

      if (object != null)
      {
        return object.cdoView();
      }

      return session;
    }

    private boolean determineOffline()
    {
      IRegistry<String, Object> properties = session.properties();
      return properties.containsKey("org.eclipse.emf.cdo.workspace.CDOWorkspace");
    }

    /**
     * @author Eike Stepper
     * @since 4.4
     */
    public static class IllegalInputException extends Exception
    {
      private static final long serialVersionUID = 1L;

      public IllegalInputException(String message)
      {
        super(message);
      }
    }
  }

  /**
   * Provides the content of a CommitHistoryComposite, i.e., the elements of a {@link CDOCommitHistory}.
   *
   * @author Eike Stepper
   */
  public static class ContentProvider extends StructuredContentProvider<CDOCommitHistory>
  {
    public Object[] getElements(Object inputElement)
    {
      return ((CDOCommitHistory)inputElement).getElements();
    }

    @Override
    protected void connectInput(CDOCommitHistory history)
    {
      history.addListener(this);
    }

    @Override
    protected void disconnectInput(CDOCommitHistory history)
    {
      history.removeListener(this);
    }
  }

  /**
   * A {@link TableLabelProvider} for the content of a CommitHistoryComposite, i.e., the elements of a {@link CDOCommitHistory}.
   *
   * @author Eike Stepper
   */
  public static class LabelProvider extends TableLabelProvider<CDOCommitInfo>
  {
    private static final ImageDescriptor COMMIT = SharedIcons.getDescriptor(SharedIcons.OBJ_COMMIT);

    private static final ImageDescriptor PERSON = SharedIcons.getDescriptor(SharedIcons.OBJ_PERSON);

    private static final ImageDescriptor PERSON_ME = SharedIcons.getDescriptor(SharedIcons.OBJ_PERSON_ME);

    private static final ImageDescriptor BRANCH = SharedIcons.getDescriptor(SharedIcons.OBJ_BRANCH);

    private static final ImageDescriptor BRANCH_GRAY = SharedIcons.getDescriptor(SharedIcons.OBJ_BRANCH_GRAY);

    private String localUserID;

    private CDOBranch inputBranch;

    private boolean formatTimeStamps = true;

    public LabelProvider()
    {
      addColumn(new Column<CDOCommitInfo>("Time", 160)
      {
        @Override
        public String getText(CDOCommitInfo commitInfo)
        {
          if (commitInfo instanceof CDOCommitHistory.TriggerLoadElement)
          {
            return StringUtil.EMPTY;
          }

          if (formatTimeStamps)
          {
            return CDOCommonUtil.formatTimeStamp(commitInfo.getTimeStamp());
          }

          return "" + commitInfo.getTimeStamp();
        }

        @Override
        public Image getImage(CDOCommitInfo commitInfo)
        {
          if (commitInfo instanceof CDOCommitHistory.TriggerLoadElement)
          {
            return null;
          }

          return (Image)getResource(COMMIT);
        }
      });

      addColumn(new Column<CDOCommitInfo>("Comment", 250)
      {
        @Override
        public String getText(CDOCommitInfo commitInfo)
        {
          return commitInfo.getComment();
        }
      });

      addColumn(new Column<CDOCommitInfo>("User", 120)
      {
        @Override
        public String getText(CDOCommitInfo commitInfo)
        {
          if (commitInfo instanceof CDOCommitHistory.TriggerLoadElement)
          {
            return StringUtil.EMPTY;
          }

          return commitInfo.getUserID();
        }

        @Override
        public Image getImage(CDOCommitInfo commitInfo)
        {
          if (commitInfo instanceof CDOCommitHistory.TriggerLoadElement)
          {
            return null;
          }

          String userID = commitInfo.getUserID();
          if (userID != null)
          {
            if (userID.equals(localUserID))
            {
              return (Image)getResource(PERSON_ME);
            }

            return (Image)getResource(PERSON);
          }

          return null;
        }
      });

      addColumn(new Column<CDOCommitInfo>("Branch", 160)
      {
        @Override
        public String getText(CDOCommitInfo commitInfo)
        {
          if (commitInfo instanceof CDOCommitHistory.TriggerLoadElement)
          {
            return StringUtil.EMPTY;
          }

          CDOBranch commitBranch = commitInfo.getBranch();
          long timeStamp = commitInfo.getTimeStamp();

          StringBuilder builder = null;
          for (CDOBranch childBranch : commitBranch.getBranches())
          {
            if (childBranch.getBase().getTimeStamp() == timeStamp)
            {
              if (builder == null)
              {
                builder = new StringBuilder(commitBranch.getPathName());
              }

              builder.append(", ");
              builder.append(childBranch.getPathName());
            }
          }

          if (builder != null)
          {
            return builder.toString();
          }

          return commitBranch.getPathName();
        }

        @Override
        public Image getImage(CDOCommitInfo commitInfo)
        {
          if (commitInfo instanceof CDOCommitHistory.TriggerLoadElement)
          {
            return null;
          }

          if (inputBranch == null || inputBranch == commitInfo.getBranch())
          {
            return (Image)getResource(BRANCH);
          }

          return (Image)getResource(BRANCH_GRAY);
        }
      });
    }

    public String getLocalUserID()
    {
      return localUserID;
    }

    public void setLocalUserID(String localUserID)
    {
      this.localUserID = localUserID;
    }

    public CDOBranch getInputBranch()
    {
      return inputBranch;
    }

    public void setInputBranch(CDOBranch inputBranch)
    {
      this.inputBranch = inputBranch;
    }

    public boolean isFormatTimeStamps()
    {
      return formatTimeStamps;
    }

    public void setFormatTimeStamps(boolean formatTimeStamps)
    {
      this.formatTimeStamps = formatTimeStamps;
    }
  }
}
