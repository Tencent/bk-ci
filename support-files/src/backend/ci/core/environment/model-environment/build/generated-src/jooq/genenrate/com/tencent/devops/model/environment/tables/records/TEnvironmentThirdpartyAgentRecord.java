/*
 * This file is generated by jOOQ.
*/
package com.tencent.devops.model.environment.tables.records;


import com.tencent.devops.model.environment.tables.TEnvironmentThirdpartyAgent;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record19;
import org.jooq.Row19;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TEnvironmentThirdpartyAgentRecord extends UpdatableRecordImpl<TEnvironmentThirdpartyAgentRecord> implements Record19<Long, Long, String, String, String, String, String, Integer, String, String, LocalDateTime, String, String, String, String, Integer, String, String, String> {

    private static final long serialVersionUID = -1323847015;

    /**
     * Setter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.ID</code>.
     */
    public TEnvironmentThirdpartyAgentRecord setId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.ID</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.NODE_ID</code>.
     */
    public TEnvironmentThirdpartyAgentRecord setNodeId(Long value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.NODE_ID</code>.
     */
    public Long getNodeId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.PROJECT_ID</code>.
     */
    public TEnvironmentThirdpartyAgentRecord setProjectId(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.PROJECT_ID</code>.
     */
    public String getProjectId() {
        return (String) get(2);
    }

    /**
     * Setter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.HOSTNAME</code>.
     */
    public TEnvironmentThirdpartyAgentRecord setHostname(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.HOSTNAME</code>.
     */
    public String getHostname() {
        return (String) get(3);
    }

    /**
     * Setter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.IP</code>.
     */
    public TEnvironmentThirdpartyAgentRecord setIp(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.IP</code>.
     */
    public String getIp() {
        return (String) get(4);
    }

    /**
     * Setter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.OS</code>.
     */
    public TEnvironmentThirdpartyAgentRecord setOs(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.OS</code>.
     */
    public String getOs() {
        return (String) get(5);
    }

    /**
     * Setter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.DETECT_OS</code>.
     */
    public TEnvironmentThirdpartyAgentRecord setDetectOs(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.DETECT_OS</code>.
     */
    public String getDetectOs() {
        return (String) get(6);
    }

    /**
     * Setter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.STATUS</code>.
     */
    public TEnvironmentThirdpartyAgentRecord setStatus(Integer value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.STATUS</code>.
     */
    public Integer getStatus() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.SECRET_KEY</code>.
     */
    public TEnvironmentThirdpartyAgentRecord setSecretKey(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.SECRET_KEY</code>.
     */
    public String getSecretKey() {
        return (String) get(8);
    }

    /**
     * Setter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.CREATED_USER</code>.
     */
    public TEnvironmentThirdpartyAgentRecord setCreatedUser(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.CREATED_USER</code>.
     */
    public String getCreatedUser() {
        return (String) get(9);
    }

    /**
     * Setter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.CREATED_TIME</code>.
     */
    public TEnvironmentThirdpartyAgentRecord setCreatedTime(LocalDateTime value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.CREATED_TIME</code>.
     */
    public LocalDateTime getCreatedTime() {
        return (LocalDateTime) get(10);
    }

    /**
     * Setter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.START_REMOTE_IP</code>.
     */
    public TEnvironmentThirdpartyAgentRecord setStartRemoteIp(String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.START_REMOTE_IP</code>.
     */
    public String getStartRemoteIp() {
        return (String) get(11);
    }

    /**
     * Setter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.GATEWAY</code>.
     */
    public TEnvironmentThirdpartyAgentRecord setGateway(String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.GATEWAY</code>.
     */
    public String getGateway() {
        return (String) get(12);
    }

    /**
     * Setter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.VERSION</code>.
     */
    public TEnvironmentThirdpartyAgentRecord setVersion(String value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.VERSION</code>.
     */
    public String getVersion() {
        return (String) get(13);
    }

    /**
     * Setter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.MASTER_VERSION</code>.
     */
    public TEnvironmentThirdpartyAgentRecord setMasterVersion(String value) {
        set(14, value);
        return this;
    }

    /**
     * Getter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.MASTER_VERSION</code>.
     */
    public String getMasterVersion() {
        return (String) get(14);
    }

    /**
     * Setter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.PARALLEL_TASK_COUNT</code>.
     */
    public TEnvironmentThirdpartyAgentRecord setParallelTaskCount(Integer value) {
        set(15, value);
        return this;
    }

    /**
     * Getter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.PARALLEL_TASK_COUNT</code>.
     */
    public Integer getParallelTaskCount() {
        return (Integer) get(15);
    }

    /**
     * Setter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.AGENT_INSTALL_PATH</code>.
     */
    public TEnvironmentThirdpartyAgentRecord setAgentInstallPath(String value) {
        set(16, value);
        return this;
    }

    /**
     * Getter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.AGENT_INSTALL_PATH</code>.
     */
    public String getAgentInstallPath() {
        return (String) get(16);
    }

    /**
     * Setter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.STARTED_USER</code>.
     */
    public TEnvironmentThirdpartyAgentRecord setStartedUser(String value) {
        set(17, value);
        return this;
    }

    /**
     * Getter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.STARTED_USER</code>.
     */
    public String getStartedUser() {
        return (String) get(17);
    }

    /**
     * Setter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.AGENT_ENVS</code>.
     */
    public TEnvironmentThirdpartyAgentRecord setAgentEnvs(String value) {
        set(18, value);
        return this;
    }

    /**
     * Getter for <code>devops_environment.T_ENVIRONMENT_THIRDPARTY_AGENT.AGENT_ENVS</code>.
     */
    public String getAgentEnvs() {
        return (String) get(18);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record19 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row19<Long, Long, String, String, String, String, String, Integer, String, String, LocalDateTime, String, String, String, String, Integer, String, String, String> fieldsRow() {
        return (Row19) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row19<Long, Long, String, String, String, String, String, Integer, String, String, LocalDateTime, String, String, String, String, Integer, String, String, String> valuesRow() {
        return (Row19) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field2() {
        return TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT.NODE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT.PROJECT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT.HOSTNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT.IP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT.OS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT.DETECT_OS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field8() {
        return TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT.STATUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT.SECRET_KEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT.CREATED_USER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field11() {
        return TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT.CREATED_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field12() {
        return TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT.START_REMOTE_IP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field13() {
        return TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT.GATEWAY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field14() {
        return TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT.VERSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field15() {
        return TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT.MASTER_VERSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field16() {
        return TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT.PARALLEL_TASK_COUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field17() {
        return TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT.AGENT_INSTALL_PATH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field18() {
        return TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT.STARTED_USER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field19() {
        return TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT.AGENT_ENVS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value2() {
        return getNodeId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getProjectId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getHostname();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getIp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getOs();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getDetectOs();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value8() {
        return getStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getSecretKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getCreatedUser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value11() {
        return getCreatedTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value12() {
        return getStartRemoteIp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value13() {
        return getGateway();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value14() {
        return getVersion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value15() {
        return getMasterVersion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value16() {
        return getParallelTaskCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value17() {
        return getAgentInstallPath();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value18() {
        return getStartedUser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value19() {
        return getAgentEnvs();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TEnvironmentThirdpartyAgentRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TEnvironmentThirdpartyAgentRecord value2(Long value) {
        setNodeId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TEnvironmentThirdpartyAgentRecord value3(String value) {
        setProjectId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TEnvironmentThirdpartyAgentRecord value4(String value) {
        setHostname(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TEnvironmentThirdpartyAgentRecord value5(String value) {
        setIp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TEnvironmentThirdpartyAgentRecord value6(String value) {
        setOs(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TEnvironmentThirdpartyAgentRecord value7(String value) {
        setDetectOs(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TEnvironmentThirdpartyAgentRecord value8(Integer value) {
        setStatus(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TEnvironmentThirdpartyAgentRecord value9(String value) {
        setSecretKey(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TEnvironmentThirdpartyAgentRecord value10(String value) {
        setCreatedUser(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TEnvironmentThirdpartyAgentRecord value11(LocalDateTime value) {
        setCreatedTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TEnvironmentThirdpartyAgentRecord value12(String value) {
        setStartRemoteIp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TEnvironmentThirdpartyAgentRecord value13(String value) {
        setGateway(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TEnvironmentThirdpartyAgentRecord value14(String value) {
        setVersion(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TEnvironmentThirdpartyAgentRecord value15(String value) {
        setMasterVersion(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TEnvironmentThirdpartyAgentRecord value16(Integer value) {
        setParallelTaskCount(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TEnvironmentThirdpartyAgentRecord value17(String value) {
        setAgentInstallPath(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TEnvironmentThirdpartyAgentRecord value18(String value) {
        setStartedUser(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TEnvironmentThirdpartyAgentRecord value19(String value) {
        setAgentEnvs(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TEnvironmentThirdpartyAgentRecord values(Long value1, Long value2, String value3, String value4, String value5, String value6, String value7, Integer value8, String value9, String value10, LocalDateTime value11, String value12, String value13, String value14, String value15, Integer value16, String value17, String value18, String value19) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        value18(value18);
        value19(value19);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TEnvironmentThirdpartyAgentRecord
     */
    public TEnvironmentThirdpartyAgentRecord() {
        super(TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT);
    }

    /**
     * Create a detached, initialised TEnvironmentThirdpartyAgentRecord
     */
    public TEnvironmentThirdpartyAgentRecord(Long id, Long nodeId, String projectId, String hostname, String ip, String os, String detectOs, Integer status, String secretKey, String createdUser, LocalDateTime createdTime, String startRemoteIp, String gateway, String version, String masterVersion, Integer parallelTaskCount, String agentInstallPath, String startedUser, String agentEnvs) {
        super(TEnvironmentThirdpartyAgent.T_ENVIRONMENT_THIRDPARTY_AGENT);

        set(0, id);
        set(1, nodeId);
        set(2, projectId);
        set(3, hostname);
        set(4, ip);
        set(5, os);
        set(6, detectOs);
        set(7, status);
        set(8, secretKey);
        set(9, createdUser);
        set(10, createdTime);
        set(11, startRemoteIp);
        set(12, gateway);
        set(13, version);
        set(14, masterVersion);
        set(15, parallelTaskCount);
        set(16, agentInstallPath);
        set(17, startedUser);
        set(18, agentEnvs);
    }
}
