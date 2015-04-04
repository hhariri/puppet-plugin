<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>

<l:settingsGroup title="Puppet Parameters">


    <props:workingDirectory />

    <tr>
        <th>
            <label for="puppetRootPath">Puppet Home:</label>
        </th>
        <td>
            <div class="completionIconWrapper">
                <props:textProperty name="puppetRootPath" className="longField"/>
            </div>
            <span class="smallNote">Puppet home folder</span>
        </td>
    </tr>
    <tr>
        <th>
            <label for="puppetRootPath1">Puppet Classes:</label>
        </th>
        <td>
            <div class="completionIconWrapper">
                <props:textProperty name="puppetRootPath1" className="longField"/>
            </div>
            <span class="smallNote">Puppet home folder</span>
        </td>
    </tr>

    <tr>
        <th>
            <label for="puppetClassify">Node Category:</label>
        </th>
        <td>
            <div class="completionIconWrapper">
                <props:textProperty name="puppetClassify" className="longField"/>
            </div>
            <span class="smallNote">Puppet Classification</span>
        </td>
    </tr>

</l:settingsGroup>
