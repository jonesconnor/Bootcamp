using Microsoft.Extensions.Hosting;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace test
{
    internal class Startup
    {
            public void ConfigureHost(IHostBuilder hostBuilder) =>
                hostBuilder
                    .ConfigureHostConfiguration(builder => { })
                    .ConfigureAppConfiguration((context, builder) => { });
       
    }
}
