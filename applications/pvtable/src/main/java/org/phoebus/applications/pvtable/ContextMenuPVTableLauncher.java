/*******************************************************************************
 * Copyright (c) 2017 Oak Ridge National Laboratory.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.phoebus.applications.pvtable;

import java.util.List;

import org.phoebus.core.types.ProcessVariable;
import org.phoebus.framework.selection.Selection;
import org.phoebus.framework.workbench.ApplicationService;
import org.phoebus.ui.application.ContextMenuHelper;
import org.phoebus.ui.spi.ContextMenuEntry;

import javafx.scene.image.Image;

/** Entry for context menues that starts PV Table for selected ProcessVariable
 *
 *  @author Kay Kasemir
 */
@SuppressWarnings({ "rawtypes", "nls" })
public class ContextMenuPVTableLauncher implements ContextMenuEntry<ProcessVariable>
{
    private static final List<Class> supportedTypes = List.of(ProcessVariable.class);

    private static final Image icon = ContextMenuHelper.loadIcon(PVTableApplication.class, "/icons/pvtable.png");

    @Override
    public String getName()
    {
        return PVTableApplication.DISPLAY_NAME;
    }

    @Override
    public Image getIcon()
    {
        return icon;
    }

    @Override
    public List<Class> getSupportedTypes()
    {
        return supportedTypes;
    }

    @Override
    public ProcessVariable callWithSelection(final Selection selection) throws Exception
    {
        final PVTableInstance instance = (PVTableInstance) ApplicationService.findApplication(PVTableApplication.NAME).create();
        final List<ProcessVariable> pvs = selection.getSelections();
        for (ProcessVariable pv : pvs)
            instance.getModel().addItem(pv.getName());
        return null;
    }
}