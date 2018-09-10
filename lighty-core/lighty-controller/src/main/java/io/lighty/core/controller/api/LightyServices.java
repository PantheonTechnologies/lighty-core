/*
 * Copyright (c) 2018 Pantheon Technologies s.r.o. All Rights Reserved.
 *
 * This Source Code Form is subject to the terms of the lighty.io-core
 * Fair License 5, version 0.9.1. You may obtain a copy of the License
 * at: https://github.com/PantheonTechnologies/lighty-core/LICENSE.md
 */
package io.lighty.core.controller.api;

import io.netty.channel.EventLoopGroup;
import io.netty.util.Timer;
import io.netty.util.concurrent.EventExecutor;
import org.opendaylight.controller.cluster.ActorSystemProvider;
import org.opendaylight.controller.cluster.datastore.DistributedDataStoreInterface;
import org.opendaylight.controller.cluster.sharding.DistributedShardFactory;
import org.opendaylight.controller.config.threadpool.ScheduledThreadPool;
import org.opendaylight.controller.config.threadpool.ThreadPool;
import org.opendaylight.controller.sal.binding.api.NotificationProviderService;
import org.opendaylight.controller.sal.binding.api.RpcProviderRegistry;
import org.opendaylight.infrautils.diagstatus.DiagStatusService;
import org.opendaylight.mdsal.binding.api.ActionProviderService;
import org.opendaylight.mdsal.binding.api.ActionService;
import org.opendaylight.mdsal.binding.api.BindingService;
import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.DataTreeService;
import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.mdsal.binding.api.NotificationPublishService;
import org.opendaylight.mdsal.binding.api.NotificationService;
import org.opendaylight.mdsal.binding.api.RpcConsumerRegistry;
import org.opendaylight.mdsal.binding.api.RpcProviderService;
import org.opendaylight.mdsal.binding.dom.adapter.BindingAdapterFactory;
import org.opendaylight.mdsal.binding.dom.codec.api.BindingCodecTreeFactory;
import org.opendaylight.mdsal.binding.dom.codec.api.BindingNormalizedNodeSerializer;
import org.opendaylight.mdsal.dom.api.DOMActionProviderService;
import org.opendaylight.mdsal.dom.api.DOMDataBroker;
import org.opendaylight.mdsal.dom.api.DOMDataTreeService;
import org.opendaylight.mdsal.dom.api.DOMDataTreeShardingService;
import org.opendaylight.mdsal.dom.api.DOMMountPointService;
import org.opendaylight.mdsal.dom.api.DOMNotificationPublishService;
import org.opendaylight.mdsal.dom.api.DOMNotificationService;
import org.opendaylight.mdsal.dom.api.DOMRpcProviderService;
import org.opendaylight.mdsal.dom.api.DOMRpcService;
import org.opendaylight.mdsal.dom.api.DOMSchemaService;
import org.opendaylight.mdsal.dom.api.DOMService;
import org.opendaylight.mdsal.dom.api.DOMYangTextSourceProvider;
import org.opendaylight.mdsal.dom.spi.DOMNotificationSubscriptionListenerRegistry;
import org.opendaylight.mdsal.eos.binding.api.EntityOwnershipService;
import org.opendaylight.mdsal.eos.dom.api.DOMEntityOwnershipService;
import org.opendaylight.mdsal.singleton.common.api.ClusterSingletonServiceProvider;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.md.sal.cluster.admin.rev151013.ClusterAdminService;
import org.opendaylight.yangtools.yang.model.api.SchemaContextProvider;

/**
 * This interface provides methods to access ODL core services
 * provided by {@link LightyController} module.
 *
 * @author juraj.veverka
 */
public interface LightyServices extends LightyModuleRegistryService {

    /**
     * Allows to create {@link BindingService Binding service} from {@link DOMService DOM service}:
     * <ul>
     * <li>{@link DataBroker}</li>
     * <li>{@link DataTreeService}</li>
     * <li>{@link MountPointService}</li>
     * <li>{@link NotificationService}</li>
     * <li>{@link NotificationPublishService}</li>
     * <li>{@link RpcConsumerRegistry}</li>
     * <li>{@link RpcProviderRegistry}</li>
     * <li>{@link ActionService}</li>
     * <li>{@link ActionProviderService}</li>
     * </ul>
     * @return factory for binding services
     */
    BindingAdapterFactory getBindingAdapterFactory();

    DiagStatusService getDiagStatusService();

    ActorSystemProvider getActorSystemProvider();

    SchemaContextProvider getSchemaContextProvider();

    DOMSchemaService getDOMSchemaService();

    DOMYangTextSourceProvider getDOMYangTextSourceProvider();

    DOMNotificationSubscriptionListenerRegistry getDOMNotificationSubscriptionListenerRegistry();

    DistributedDataStoreInterface getConfigDatastore();

    DistributedDataStoreInterface getOperationalDatastore();

    DOMDataTreeShardingService getDOMDataTreeShardingService();

    DOMDataTreeService getDOMDataTreeService();

    DistributedShardFactory getDistributedShardFactory();

    BindingNormalizedNodeSerializer getBindingNormalizedNodeSerializer();

    BindingCodecTreeFactory getBindingCodecTreeFactory();

    DOMEntityOwnershipService getDOMEntityOwnershipService();

    EntityOwnershipService getEntityOwnershipService();

    ClusterAdminService getClusterAdminRPCService();

    ClusterSingletonServiceProvider getClusterSingletonServiceProvider();

    EventExecutor getEventExecutor();

    EventLoopGroup getBossGroup();

    EventLoopGroup getWorkerGroup();

    ThreadPool getThreadPool();

    ScheduledThreadPool getScheduledThreaPool();

    Timer getTimer();

    DOMMountPointService getDOMMountPointService();

    DOMNotificationPublishService getDOMNotificationPublishService();

    DOMNotificationService getDOMNotificationService();

    DOMDataBroker getClusteredDOMDataBroker();

    DOMDataBroker getPingPongDataBroker();

    DOMRpcService getDOMRpcService();

    DOMRpcProviderService getDOMRpcProviderService();

    RpcProviderService getRpcProviderService();

    MountPointService getBindingMountPointService();

    NotificationService getNotificationService();

    NotificationPublishService getBindingNotificationPublishService();

    DataBroker getBindingDataBroker();

    DataBroker getBindingPingPongDataBroker();

    ActionProviderService getActionProviderService();

    DOMActionProviderService getDOMActionProviderService();

    @Deprecated
    NotificationProviderService getControllerNotificationProviderService();

    @Deprecated
    org.opendaylight.controller.md.sal.dom.spi.DOMNotificationSubscriptionListenerRegistry
    getControllerDOMNotificationSubscriptionListenerRegistry();

    @Deprecated
    org.opendaylight.controller.md.sal.dom.api.DOMMountPointService getControllerDOMMountPointService();

    @Deprecated
    org.opendaylight.controller.md.sal.dom.api.DOMNotificationPublishService
    getControllerDOMNotificationPublishService();

    @Deprecated
    org.opendaylight.controller.md.sal.dom.api.DOMNotificationService getControllerDOMNotificationService();

    @Deprecated
    org.opendaylight.controller.md.sal.dom.api.DOMDataBroker getControllerClusteredDOMDataBroker();

    @Deprecated
    org.opendaylight.controller.md.sal.dom.api.DOMDataBroker getControllerPingPongDataBroker();

    @Deprecated
    org.opendaylight.controller.md.sal.dom.api.DOMRpcService getControllerDOMRpcService();

    @Deprecated
    org.opendaylight.controller.md.sal.dom.api.DOMRpcProviderService getControllerDOMRpcProviderService();

    @Deprecated
    RpcProviderRegistry getControllerRpcProviderRegistry();

    @Deprecated
    org.opendaylight.controller.md.sal.binding.api.MountPointService getControllerBindingMountPointService();

    @Deprecated
    org.opendaylight.controller.md.sal.binding.api.NotificationService getControllerBindingNotificationService();

    @Deprecated
    org.opendaylight.controller.md.sal.binding.api.DataBroker getControllerBindingDataBroker();

    @Deprecated
    org.opendaylight.controller.md.sal.binding.api.DataBroker getControllerBindingPingPongDataBroker();

    @Deprecated
    org.opendaylight.controller.md.sal.binding.api.NotificationPublishService
    getControllerBindingNotificationPublishService();

    @Deprecated
    org.opendaylight.controller.md.sal.dom.api.DOMActionProviderService getControllerDomActionProviderService();

    @Deprecated
    org.opendaylight.controller.md.sal.binding.api.ActionProviderService getControllerActionProviderService();
}
