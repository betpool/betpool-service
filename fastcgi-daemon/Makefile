CXX = g++
CXXFLAGS = -std=c++0x -fPIC
LDFLAGS = -shared
LIBS = -lfastcgi-daemon2

SOURCES = Summer.cpp
OBJECTS = $(SOURCES:.cpp=.o)

TARGET=libsummer.so

$(TARGET): $(OBJECTS)
	$(CXX) $(CXXFLAGS) $(OBJECTS) -o $@ $(LDFLAGS) $(LIBS)

.PHONY: clean
clean:
	rm -f $(OBJECTS) $(TARGET)
