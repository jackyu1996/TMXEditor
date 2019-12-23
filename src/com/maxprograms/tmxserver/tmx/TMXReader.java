/*****************************************************************************
Copyright (c) 2018-2019 - Maxprograms,  http://www.maxprograms.com/

Permission is hereby granted, free of charge, to any person obtaining a copy of 
this software and associated documentation files (the "Software"), to compile, 
modify and use the Software in its executable form without restrictions.

Redistribution of this Software or parts of it in any form (source code or 
executable binaries) requires prior written permission from Maxprograms.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE 
SOFTWARE.
*****************************************************************************/
package com.maxprograms.tmxserver.tmx;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.maxprograms.xml.CustomErrorHandler;
import com.maxprograms.xml.SAXBuilder;

public class TMXReader {

	private TMXContentHandler handler;
	private StoreInterface store;
	private SAXBuilder builder;

	public TMXReader(StoreInterface store) {
		this.store = store;

		builder = new SAXBuilder();
		builder.setEntityResolver(new TMXResolver());
		handler = new TMXContentHandler(store);
		builder.setContentHandler(handler);
		builder.setErrorHandler(new CustomErrorHandler());
	}

	public void parse(File file) throws SAXException, IOException, ParserConfigurationException {
		TmxUtils.resetTags();
		builder.build(file);
		store.commit();
	}

}
