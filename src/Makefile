APP_PATH := $(CATALINA_HOME)\webapps\axis
AXIS_PATH := $(LOCALAPPDATA)\scoop\apps\axis\1.4\axis-1_4
CLASSPATH := $(AXIS_PATH)\lib\*
BIN_PATH := ../bin

SERVER_SRC := $(wildcard server/*.java)
JWS_SRC := $(patsubst server/%.java,$(APP_PATH)\\%.jws,$(SERVER_SRC))
WSDL_SRC := $(wildcard *.jws.xml)

CLIENT_SRC := $(shell find client -name *.java)
CLIENT_OBJ := $(patsubst client/%.java,$(BIN_PATH)/%.class,$(CLIENT_SRC))

all: build

build: clean build-server WSDL build-client

build-client:
	@cp log4j.properties $(BIN_PATH)
	@mkdir -p "$(@D)"
	javac -classpath "$(CLASSPATH)" -d $(BIN_PATH) $(CLIENT_SRC)

copy_to_server = $(shell cp $(file) $(patsubst server/%.java,$(APP_PATH)/%.jws,$(file)))
build-server:
	$(foreach file, $(SERVER_SRC), $(copy_to_server))

export_classpath = export CLASSPATH="$(CLASSPATH)"
compile_wsdl = $(shell $(export_classpath) && java org.apache.axis.wsdl.WSDL2Java $(file))

WSDL:
	$(RM) -r client/localhost
	$(foreach file, $(WSDL_SRC), $(compile_wsdl))
	@mv localhost client

clean:
	$(RM) -r $(JWS_SRC) $(BIN_PATH)/*
