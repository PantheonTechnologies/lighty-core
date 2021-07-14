/*
 * Copyright (c) 2021 PANTHEON.tech s.r.o. All Rights Reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-v10.html
 */

package io.lighty.modules.gnmi.connector.gnmi.wrappers;

import static io.lighty.modules.gnmi.connector.gnmi.util.GnmiHelper.pathBuilder;

import gnmi.Gnmi.Path.Builder;
import java.io.IOException;
import java.util.Optional;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.mdsal.binding.dom.codec.impl.BindingCodecContext;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.QNameModule;
import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier;
import org.opendaylight.yangtools.yang.data.api.schema.NormalizedNode;
import org.opendaylight.yangtools.yang.data.api.schema.stream.NormalizedNodeWriter;
import org.opendaylight.yangtools.yang.data.impl.schema.ImmutableNodes;
import org.opendaylight.yangtools.yang.model.api.Module;

public class PathWrapper {
    private final BindingCodecContext bindingCodecContext;

    public PathWrapper(BindingCodecContext bindingCodecContext) {
        this.bindingCodecContext = bindingCodecContext;
    }

    public <T extends DataObject> Builder getPathBuilder(InstanceIdentifier<T> instanceIdentifier)
            throws IOException {
        return getPathBuilder(bindingCodecContext.toYangInstanceIdentifier(instanceIdentifier));
    }

    public Builder getPathBuilder(YangInstanceIdentifier instanceIdentifier) throws IOException {
        NormalizedNode<?, ?> normalizedNode =
                ImmutableNodes.fromInstanceId(this.bindingCodecContext.getRuntimeContext().getEffectiveModelContext(),
                        instanceIdentifier);
        String prefix = getPrefix(instanceIdentifier);
        if (prefix != null) {
            InstanceIdentifierNodeStreamWriter instanceIdentifierNodeStreamWriter =
                    new InstanceIdentifierNodeStreamWriter(prefix);
            NormalizedNodeWriter normalizedNodeWriter =
                    NormalizedNodeWriter.forStreamWriter(instanceIdentifierNodeStreamWriter, false);
            normalizedNodeWriter.write(normalizedNode);
            return pathBuilder(instanceIdentifierNodeStreamWriter.getUrl());
        }
        return null;
    }

    private String getPrefix(YangInstanceIdentifier instanceIdentifier) {
        @NonNull QNameModule targetQname = instanceIdentifier.getLastPathArgument().getNodeType().getModule();
        Optional<Module> targetModule =
                this.bindingCodecContext.getRuntimeContext().getEffectiveModelContext().findModule(targetQname);
        return targetModule.map(Module::getName).orElse(null);
    }
}
