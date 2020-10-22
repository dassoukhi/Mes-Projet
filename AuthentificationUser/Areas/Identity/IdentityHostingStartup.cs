using System;
using AuthentificationUser.Areas.Identity.Data;
using AuthentificationUser.Data;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.UI;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

[assembly: HostingStartup(typeof(AuthentificationUser.Areas.Identity.IdentityHostingStartup))]
namespace AuthentificationUser.Areas.Identity
{
    public class IdentityHostingStartup : IHostingStartup
    {
        public void Configure(IWebHostBuilder builder)
        {
            builder.ConfigureServices((context, services) => {
                services.AddDbContext<AuthentificationUserDbContext>(options =>
                    options.UseSqlServer(
                        context.Configuration.GetConnectionString("AuthentificationUserDbContextConnection")));

                services.AddDefaultIdentity<ApplicationUser>(options => {
                    options.SignIn.RequireConfirmedAccount = false;
                    options.Password.RequireLowercase = false;
                    options.Password.RequireUppercase = false;
                })
                    .AddEntityFrameworkStores<AuthentificationUserDbContext>();
            });
        }
    }
}