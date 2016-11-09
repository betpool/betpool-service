#include <fastcgi2/component.h>
#include <fastcgi2/component_factory.h>
#include <fastcgi2/handler.h>
#include <fastcgi2/request.h>

#include <iostream>
#include <string>

class Summer : virtual public fastcgi::Component, virtual public fastcgi::Handler {

public:
    Summer(fastcgi::ComponentContext *context) :
    fastcgi::Component(context) {
    }
    virtual ~Summer() {
    }

public:
    virtual void onLoad() {
    }
    virtual void onUnload() {
    }
    virtual void handleRequest(fastcgi::Request *request, fastcgi::HandlerContext *context) 
    {
        request->setContentType("text/plain");
        long n = std::stol(request->getArg("n"));
        long long sum = 0;// (1 + n) / 2 * n
        for (int i = 1; i <= n; ++i) {
            sum += i;
        }
        std::string resp = "Sum(" + std::to_string(n) + ")=" + std::to_string(sum) + "\n";
        request->write(resp.c_str(), resp.size());
    }

};

FCGIDAEMON_REGISTER_FACTORIES_BEGIN()
FCGIDAEMON_ADD_DEFAULT_FACTORY("summer_factory", Summer)
FCGIDAEMON_REGISTER_FACTORIES_END()
