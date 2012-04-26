/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */

package org.jboss.as.console.client.shared.subsys.web.model;

import org.jboss.as.console.client.widgets.forms.Address;
import org.jboss.as.console.client.widgets.forms.Binding;

/**
 * @author Heiko Braun
 * @date 5/11/11
 */
@Address("/subsystem=web/configuration=jsp-configuration")
public interface JSPContainerConfiguration {

    boolean isDevelopment();
    void setDevelopment(boolean b);

    @Binding(detypedName = "keep-generated")
    boolean isKeepGenerated();
    void setKeepGenerated(boolean b);

    @Binding(detypedName = "recompile-on-fail")
    boolean isRecompile();
    void setRecompile(boolean b);

    @Binding(detypedName = "display-source-fragment")
    boolean isDisplaySource();
    void setDisplaySource(boolean b);

    boolean isDisabled();
    void setDisabled(boolean b);

    @Binding(detypedName = "check-interval")
    int getCheckInterval();
    void setCheckInterval(int i);

    //boolean isListings();
    //void setListings(boolean b);

    @Binding(detypedName = "instance-id")
   	String getInstanceId();
   	void setInstanceId(String instanceId);

}
