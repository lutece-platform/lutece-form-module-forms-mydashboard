/*
 * Copyright (c) 2002-2022, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.forms.modules.mydashboard.service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import fr.paris.lutece.plugins.forms.service.FormResponseService;
import fr.paris.lutece.plugins.mydashboard.service.MyDashboardComponent;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.security.LuteceUser;
import fr.paris.lutece.portal.service.security.SecurityService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.util.html.HtmlTemplate;

/**
 * 
 * MyDashboardComponentFormResponsesi
 *
 */
public class MyDashboardComponentFormResponses extends MyDashboardComponent
{

	/**
	 * 
	 */
    private static final long serialVersionUID = 2516946268291209327L;

    private static final String TEMPLATE_VIEW_FORMRESPONSES_LIST = "skin/plugins/forms/mydashboard/dashboard_formresponses.html";
    private static final String MARK_RESPONSE_LIST               = "response_list";
    private static final String DASHBOARD_COMPONENT_ID           = "formResponses.myDashboardComponentFormResponses";
    private static final String MESSAGE_COMPONENT_DESCRIPTION    = "module.forms.mydashboard.myDashboardComponentFormResponses.description";


    
	@Override
	public String getDashboardData( HttpServletRequest request )
	{
        LuteceUser user = SecurityService.getInstance( ).getRegisteredUser( request );
        if ( user != null )
        {
            Map<String, Object> model = new HashMap< >( );

            model.put( MARK_RESPONSE_LIST, FormResponseService.getInstance().getFormResponseListForUser( user ) );
            
            HtmlTemplate htmTemplate = AppTemplateService.getTemplate( TEMPLATE_VIEW_FORMRESPONSES_LIST, request.getLocale( ), model);
            
            return htmTemplate.getHtml( );
    	}
        
        return StringUtils.EMPTY;
	}
	
	@Override
	public String getComponentDescription( Locale locale )
	{
        return I18nService.getLocalizedString( MESSAGE_COMPONENT_DESCRIPTION, locale );
	}

	@Override
	public String getComponentId( )
	{
        return DASHBOARD_COMPONENT_ID;
	}

}
